import { Component, ChangeDetectionStrategy, signal } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ProfessorService } from '../shared/services/professor.service';
import { Professor } from '../shared/models/professor.model';
import { ProfessorDialogComponent } from './professor-dialog.component';

@Component({
  selector: 'app-professores',
  imports: [MatTableModule, MatButtonModule, MatIconModule],
  templateUrl: './professores.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ProfessoresComponent {
  colunas = ['nome', 'titulacao', 'acoes'];
  professores = signal<Professor[]>([]);

  constructor(
    private servico: ProfessorService,
    private dialogo: MatDialog,
    private notificacao: MatSnackBar
  ) {
    this.carregar();
  }

  carregar() {
    this.servico.listar().subscribe(dados => this.professores.set(dados));
  }

  abrir(professor?: Professor) {
    this.dialogo.open(ProfessorDialogComponent, { width: '480px', data: professor ? { ...professor } : null })
      .afterClosed().subscribe(resultado => { if (resultado) this.carregar(); });
  }

  excluir(professor: Professor) {
    if (!confirm(`Excluir ${professor.nome}?`)) return;
    this.servico.excluir(professor.id!).subscribe(() => {
      this.notificacao.open('Professor excluído', '', { duration: 2000 });
      this.carregar();
    });
  }
}
