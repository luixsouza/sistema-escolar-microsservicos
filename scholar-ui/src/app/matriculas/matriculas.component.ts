import { Component, ChangeDetectionStrategy, signal } from '@angular/core';
import { forkJoin } from 'rxjs';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatriculaService } from '../shared/services/matricula.service';
import { AlunoService } from '../shared/services/aluno.service';
import { DisciplinaService } from '../shared/services/disciplina.service';
import { Aluno } from '../shared/models/aluno.model';
import { Disciplina } from '../shared/models/disciplina.model';
import { MatriculaDialogComponent } from './matricula-dialog.component';

interface MatriculaView {
  id: number;
  alunoNome: string;
  alunoMatricula: string;
  disciplinaNome: string;
  cargaHoraria: number;
}

@Component({
  selector: 'app-matriculas',
  imports: [MatTableModule, MatButtonModule, MatIconModule],
  templateUrl: './matriculas.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class MatriculasComponent {
  columns = ['aluno', 'disciplina', 'acoes'];
  matriculas = signal<MatriculaView[]>([]);

  constructor(
    private matriculaService: MatriculaService,
    private alunoService: AlunoService,
    private disciplinaService: DisciplinaService,
    private dialog: MatDialog,
    private snack: MatSnackBar
  ) {
    this.carregar();
  }

  carregar() {
    forkJoin({
      matriculas: this.matriculaService.listar(),
      alunos: this.alunoService.listar(),
      disciplinas: this.disciplinaService.listar()
    }).subscribe(({ matriculas, alunos, disciplinas }) => {
      const alunoMap = new Map(alunos.map(a => [a.id!, a]));
      const discMap = new Map(disciplinas.map(d => [d.id!, d]));
      this.matriculas.set(matriculas.map(m => {
        const aluno = alunoMap.get(m.alunoId);
        const disc = discMap.get(m.disciplinaId);
        return {
          id: m.id!,
          alunoNome: aluno?.nome ?? `#${m.alunoId}`,
          alunoMatricula: aluno?.matricula ?? '',
          disciplinaNome: disc?.nome ?? `#${m.disciplinaId}`,
          cargaHoraria: disc?.cargaHoraria ?? 0
        };
      }));
    });
  }

  abrir() {
    this.dialog.open(MatriculaDialogComponent, { width: '480px' })
      .afterClosed().subscribe(result => { if (result) this.carregar(); });
  }

  excluir(m: MatriculaView) {
    if (!confirm(`Cancelar matricula de ${m.alunoNome} em ${m.disciplinaNome}?`)) return;
    this.matriculaService.excluir(m.id).subscribe(() => {
      this.snack.open('Matricula cancelada', '', { duration: 2000 });
      this.carregar();
    });
  }
}
