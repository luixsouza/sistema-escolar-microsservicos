import { Component, Inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DisciplinaService } from '../shared/services/disciplina.service';
import { Disciplina } from '../shared/models/disciplina.model';

@Component({
  selector: 'app-disciplina-dialog',
  imports: [FormsModule, MatDialogModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatIconModule],
  template: `
    <mat-dialog-content>
      <div class="dialog-icon">
        <mat-icon>menu_book</mat-icon>
        <div class="dialog-title-text">
          <h2>{{ data ? 'Editar' : 'Nova' }} Disciplina</h2>
          <p>{{ data ? 'Atualize os dados da disciplina' : 'Preencha os dados para cadastrar' }}</p>
        </div>
      </div>

      <mat-form-field appearance="outline" class="full-width">
        <mat-label>Nome da disciplina</mat-label>
        <input matInput [(ngModel)]="disciplina.nome" required placeholder="Ex: Programacao Orientada a Objetos">
      </mat-form-field>

      <mat-form-field appearance="outline" class="full-width">
        <mat-label>Carga horaria (horas)</mat-label>
        <input matInput [(ngModel)]="disciplina.cargaHoraria" required type="number" min="1" placeholder="Ex: 80">
        <span matTextSuffix>h</span>
      </mat-form-field>
    </mat-dialog-content>
    <mat-dialog-actions align="end">
      <button mat-button mat-dialog-close>Cancelar</button>
      <button mat-flat-button (click)="salvar()" [disabled]="!disciplina.nome || !disciplina.cargaHoraria">
        {{ data ? 'Atualizar' : 'Cadastrar' }}
      </button>
    </mat-dialog-actions>
  `
})
export class DisciplinaDialogComponent {
  disciplina: Disciplina;

  constructor(
    private ref: MatDialogRef<DisciplinaDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Disciplina | null,
    private service: DisciplinaService,
    private snack: MatSnackBar
  ) {
    this.disciplina = data ? { ...data } : { nome: '', cargaHoraria: 0 };
  }

  salvar() {
    const op = this.disciplina.id
      ? this.service.atualizar(this.disciplina.id, this.disciplina)
      : this.service.criar(this.disciplina);
    op.subscribe({
      next: () => {
        this.snack.open(this.disciplina.id ? 'Disciplina atualizada' : 'Disciplina cadastrada', '', { duration: 2000 });
        this.ref.close(true);
      },
      error: () => this.snack.open('Erro ao salvar', '', { duration: 3000 })
    });
  }
}
