import { Component, ChangeDetectionStrategy, signal } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AlunoService } from '../shared/services/aluno.service';
import { Aluno } from '../shared/models/aluno.model';
import { AlunoDialogComponent } from './aluno-dialog.component';

@Component({
  selector: 'app-alunos',
  imports: [MatTableModule, MatButtonModule, MatIconModule],
  templateUrl: './alunos.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class AlunosComponent {
  colunas = ['nome', 'matricula', 'acoes'];
  alunos = signal<Aluno[]>([]);

  constructor(
    private servico: AlunoService,
    private dialogo: MatDialog,
    private notificacao: MatSnackBar
  ) {
    this.carregar();
  }

  carregar() {
    this.servico.listar().subscribe(dados => this.alunos.set(dados));
  }

  abrir(aluno?: Aluno) {
    this.dialogo.open(AlunoDialogComponent, { width: '480px', data: aluno ? { ...aluno } : null })
      .afterClosed().subscribe(resultado => { if (resultado) this.carregar(); });
  }

  excluir(aluno: Aluno) {
    if (!confirm(`Excluir ${aluno.nome}?`)) return;
    this.servico.excluir(aluno.id!).subscribe(() => {
      this.notificacao.open('Aluno excluído', '', { duration: 2000 });
      this.carregar();
    });
  }
}
