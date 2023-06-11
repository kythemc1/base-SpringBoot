import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDemo, Demo } from 'app/shared/model/demo.model';
import { DemoService } from './demo.service';

@Component({
  selector: 'jhi-demo-update',
  templateUrl: './demo-update.component.html',
})
export class DemoUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [],
    description: [],
  });

  constructor(protected demoService: DemoService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ demo }) => {
      this.updateForm(demo);
    });
  }

  updateForm(demo: IDemo): void {
    this.editForm.patchValue({
      id: demo.id,
      name: demo.name,
      description: demo.description,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const demo = this.createFromForm();
    if (demo.id !== undefined) {
      this.subscribeToSaveResponse(this.demoService.update(demo));
    } else {
      this.subscribeToSaveResponse(this.demoService.create(demo));
    }
  }

  private createFromForm(): IDemo {
    return {
      ...new Demo(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      description: this.editForm.get(['description'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDemo>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
