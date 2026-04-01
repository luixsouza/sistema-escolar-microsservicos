import { Component, ChangeDetectionStrategy, signal } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DisciplinaService } from '../shared/services/disciplina.service';
import { Disciplina } from '../shared/models/disciplina.model';
import { DisciplinaDialogComponent } from './disciplina-dialog.component';

@Component({
  selector: 'app-disciplinas',
  imports: [MatTableModule, MatButtonModule, MatIconModule],
  templateUrl: './disciplinas.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class DisciplinasComponent {
  colunas = ['nome', 'cargaHoraria', 'acoes'];
  disciplinas = signal<Disciplina[]>([]);

  constructor(
    private servico: DisciplinaService,
    private dialogo: MatDialog,
    private notificacao: MatSnackBar
  ) {
    this.carregar();
  }

  carregar() {
    this.servico.listar().subscribe(dados => this.disciplinas.set(dados));
  }

  abrir(disciplina?: Disciplina) {
    this.dialogo.open(DisciplinaDialogComponent, { width: '480px', data: disciplina ? { ...disciplina } : null })
      .afterClosed().subscribe(resultado => { if (resultado) this.carregar(); });
  }

  excluir(disciplina: Disciplina) {
    if (!confirm(`Excluir ${disciplina.nome}?`)) return;
    this.servico.excluir(disciplina.id!).subscribe(() => {
      this.notificacao.open('Disciplina excluída', '', { duration: 2000 });
      this.carregar();
    });
  }
}
