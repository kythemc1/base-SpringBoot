import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDemo } from 'app/shared/model/demo.model';

@Component({
  selector: 'jhi-demo-detail',
  templateUrl: './demo-detail.component.html',
})
export class DemoDetailComponent implements OnInit {
  demo: IDemo | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ demo }) => (this.demo = demo));
  }

  previousState(): void {
    window.history.back();
  }
}
