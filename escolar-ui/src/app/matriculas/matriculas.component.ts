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

interface VisaoMatricula {
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
  colunas = ['aluno', 'disciplina', 'acoes'];
  matriculas = signal<VisaoMatricula[]>([]);

  constructor(
    private servicoMatricula: MatriculaService,
    private servicoAluno: AlunoService,
    private servicoDisciplina: DisciplinaService,
    private dialogo: MatDialog,
    private notificacao: MatSnackBar
  ) {
    this.carregar();
  }

  carregar() {
    forkJoin({
      matriculas: this.servicoMatricula.listar(),
      alunos: this.servicoAluno.listar(),
      disciplinas: this.servicoDisciplina.listar()
    }).subscribe(({ matriculas, alunos, disciplinas }) => {
      const mapaAlunos = new Map(alunos.map(a => [a.id!, a]));
      const mapaDisciplinas = new Map(disciplinas.map(d => [d.id!, d]));
      this.matriculas.set(matriculas.map(m => {
        const aluno = mapaAlunos.get(m.alunoId);
        const disciplina = mapaDisciplinas.get(m.disciplinaId);
        return {
          id: m.id!,
          alunoNome: aluno?.nome ?? `#${m.alunoId}`,
          alunoMatricula: aluno?.matricula ?? '',
          disciplinaNome: disciplina?.nome ?? `#${m.disciplinaId}`,
          cargaHoraria: disciplina?.cargaHoraria ?? 0
        };
      }));
    });
  }

  abrir() {
    this.dialogo.open(MatriculaDialogComponent, { width: '480px' })
      .afterClosed().subscribe(resultado => { if (resultado) this.carregar(); });
  }

  excluir(m: VisaoMatricula) {
    if (!confirm(`Cancelar matrícula de ${m.alunoNome} em ${m.disciplinaNome}?`)) return;
    this.servicoMatricula.excluir(m.id).subscribe(() => {
      this.notificacao.open('Matrícula cancelada', '', { duration: 2000 });
      this.carregar();
    });
  }
}
