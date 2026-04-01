import { Component, Inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ProfessorService } from '../shared/services/professor.service';
import { Professor } from '../shared/models/professor.model';

@Component({
  selector: 'app-professor-dialog',
  imports: [FormsModule, MatDialogModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatIconModule],
  template: `
    <mat-dialog-content>
      <div class="dialog-icon">
        <mat-icon>person</mat-icon>
        <div class="dialog-title-text">
          <h2>{{ dados ? 'Editar' : 'Novo' }} Professor</h2>
          <p>{{ dados ? 'Atualize os dados do professor' : 'Preencha os dados para cadastrar' }}</p>
        </div>
      </div>

      <mat-form-field appearance="outline" class="full-width">
        <mat-label>Nome completo</mat-label>
        <input matInput [(ngModel)]="professor.nome" required placeholder="Ex: Maria Oliveira">
      </mat-form-field>

      <mat-form-field appearance="outline" class="full-width">
        <mat-label>Email</mat-label>
        <input matInput [(ngModel)]="professor.email" required type="email" placeholder="email@ifsp.edu.br">
      </mat-form-field>

      <mat-form-field appearance="outline" class="full-width">
        <mat-label>Titulação</mat-label>
        <input matInput [(ngModel)]="professor.titulacao" required placeholder="Ex: Doutorado em Ciência da Computação">
      </mat-form-field>
    </mat-dialog-content>
    <mat-dialog-actions align="end">
      <button mat-button mat-dialog-close>Cancelar</button>
      <button mat-flat-button (click)="salvar()" [disabled]="!professor.nome || !professor.email || !professor.titulacao">
        {{ dados ? 'Atualizar' : 'Cadastrar' }}
      </button>
    </mat-dialog-actions>
  `
})
export class ProfessorDialogComponent {
  professor: Professor;

  constructor(
    private referencia: MatDialogRef<ProfessorDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public dados: Professor | null,
    private servico: ProfessorService,
    private notificacao: MatSnackBar
  ) {
    this.professor = dados ? { ...dados } : { nome: '', email: '', titulacao: '' };
  }

  salvar() {
    const operacao = this.professor.id
      ? this.servico.atualizar(this.professor.id, this.professor)
      : this.servico.criar(this.professor);
    operacao.subscribe({
      next: () => {
        this.notificacao.open(this.professor.id ? 'Professor atualizado' : 'Professor cadastrado', '', { duration: 2000 });
        this.referencia.close(true);
      },
      error: () => this.notificacao.open('Erro ao salvar', '', { duration: 3000 })
    });
  }
}
