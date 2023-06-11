import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDemo } from 'app/shared/model/demo.model';
import { DemoService } from './demo.service';

@Component({
  templateUrl: './demo-delete-dialog.component.html',
})
export class DemoDeleteDialogComponent {
  demo?: IDemo;

  constructor(protected demoService: DemoService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.demoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('demoListModification');
      this.activeModal.close();
    });
  }
}
