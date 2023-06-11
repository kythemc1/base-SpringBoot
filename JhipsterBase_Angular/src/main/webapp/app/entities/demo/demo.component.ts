import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDemo } from 'app/shared/model/demo.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { DemoService } from './demo.service';
import { DemoDeleteDialogComponent } from './demo-delete-dialog.component';

@Component({
  selector: 'jhi-demo',
  templateUrl: './demo.component.html',
})
export class DemoComponent implements OnInit, OnDestroy {
  demos: IDemo[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected demoService: DemoService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.demos = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.demoService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe((res: HttpResponse<IDemo[]>) => this.paginateDemos(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.demos = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInDemos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IDemo): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInDemos(): void {
    this.eventSubscriber = this.eventManager.subscribe('demoListModification', () => this.reset());
  }

  delete(demo: IDemo): void {
    const modalRef = this.modalService.open(DemoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.demo = demo;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateDemos(data: IDemo[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.demos.push(data[i]);
      }
    }
  }
}
