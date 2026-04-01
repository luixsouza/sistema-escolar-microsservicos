import { Component, Inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AlunoService } from '../shared/services/aluno.service';
import { Aluno } from '../shared/models/aluno.model';

@Component({
  selector: 'app-aluno-dialog',
  imports: [FormsModule, MatDialogModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatIconModule],
  template: `
    <mat-dialog-content>
      <div class="dialog-icon">
        <mat-icon>school</mat-icon>
        <div class="dialog-title-text">
          <h2>{{ dados ? 'Editar' : 'Novo' }} Aluno</h2>
          <p>{{ dados ? 'Atualize os dados do aluno' : 'Preencha os dados para cadastrar' }}</p>
        </div>
      </div>

      <mat-form-field appearance="outline" class="full-width">
        <mat-label>Nome completo</mat-label>
        <input matInput [(ngModel)]="aluno.nome" required placeholder="Ex: João da Silva">
      </mat-form-field>

      <div class="form-row">
        <mat-form-field appearance="outline">
          <mat-label>Email</mat-label>
          <input matInput [(ngModel)]="aluno.email" required type="email" placeholder="email@ifsp.edu.br">
        </mat-form-field>
        <mat-form-field appearance="outline">
          <mat-label>Matrícula</mat-label>
          <input matInput [(ngModel)]="aluno.matricula" required placeholder="Ex: 2024001">
        </mat-form-field>
      </div>
    </mat-dialog-content>
    <mat-dialog-actions align="end">
      <button mat-button mat-dialog-close>Cancelar</button>
      <button mat-flat-button (click)="salvar()" [disabled]="!aluno.nome || !aluno.email || !aluno.matricula">
        {{ dados ? 'Atualizar' : 'Cadastrar' }}
      </button>
    </mat-dialog-actions>
  `
})
export class AlunoDialogComponent {
  aluno: Aluno;

  constructor(
    private referencia: MatDialogRef<AlunoDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public dados: Aluno | null,
    private servico: AlunoService,
    private notificacao: MatSnackBar
  ) {
    this.aluno = dados ? { ...dados } : { nome: '', email: '', matricula: '' };
  }

  salvar() {
    const operacao = this.aluno.id
      ? this.servico.atualizar(this.aluno.id, this.aluno)
      : this.servico.criar(this.aluno);
    operacao.subscribe({
      next: () => {
        this.notificacao.open(this.aluno.id ? 'Aluno atualizado' : 'Aluno cadastrado', '', { duration: 2000 });
        this.referencia.close(true);
      },
      error: () => this.notificacao.open('Erro ao salvar', '', { duration: 3000 })
    });
  }
}
