import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatDialogRef, MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatriculaService } from '../shared/services/matricula.service';
import { AlunoService } from '../shared/services/aluno.service';
import { DisciplinaService } from '../shared/services/disciplina.service';
import { Aluno } from '../shared/models/aluno.model';
import { Disciplina } from '../shared/models/disciplina.model';

@Component({
  selector: 'app-matricula-dialog',
  imports: [FormsModule, MatDialogModule, MatFormFieldModule, MatSelectModule, MatButtonModule, MatIconModule],
  template: `
    <mat-dialog-content>
      <div class="dialog-icon">
        <mat-icon>assignment</mat-icon>
        <div class="dialog-title-text">
          <h2>Nova Matrícula</h2>
          <p>Selecione o aluno e a disciplina</p>
        </div>
      </div>

      <mat-form-field appearance="outline" class="full-width">
        <mat-label>Aluno</mat-label>
        <mat-select [(ngModel)]="alunoId" required>
          @for (a of alunos; track a.id) {
            <mat-option [value]="a.id">{{ a.nome }} — {{ a.matricula }}</mat-option>
          }
        </mat-select>
      </mat-form-field>

      <mat-form-field appearance="outline" class="full-width">
        <mat-label>Disciplina</mat-label>
        <mat-select [(ngModel)]="disciplinaId" required>
          @for (d of disciplinas; track d.id) {
            <mat-option [value]="d.id">{{ d.nome }} ({{ d.cargaHoraria }}h)</mat-option>
          }
        </mat-select>
      </mat-form-field>
    </mat-dialog-content>
    <mat-dialog-actions align="end">
      <button mat-button mat-dialog-close>Cancelar</button>
      <button mat-flat-button (click)="salvar()" [disabled]="!alunoId || !disciplinaId">Matricular</button>
    </mat-dialog-actions>
  `
})
export class MatriculaDialogComponent implements OnInit {
  alunos: Aluno[] = [];
  disciplinas: Disciplina[] = [];
  alunoId: number | null = null;
  disciplinaId: number | null = null;

  constructor(
    private referencia: MatDialogRef<MatriculaDialogComponent>,
    private servicoMatricula: MatriculaService,
    private servicoAluno: AlunoService,
    private servicoDisciplina: DisciplinaService,
    private notificacao: MatSnackBar
  ) {}

  ngOnInit() {
    this.servicoAluno.listar().subscribe(dados => this.alunos = dados);
    this.servicoDisciplina.listar().subscribe(dados => this.disciplinas = dados);
  }

  salvar() {
    if (!this.alunoId || !this.disciplinaId) return;
    this.servicoMatricula.criar({ alunoId: this.alunoId, disciplinaId: this.disciplinaId })
      .subscribe({
        next: () => {
          this.notificacao.open('Matrícula realizada', '', { duration: 2000 });
          this.referencia.close(true);
        },
        error: () => this.notificacao.open('Erro ao matricular', '', { duration: 3000 })
      });
  }
}
