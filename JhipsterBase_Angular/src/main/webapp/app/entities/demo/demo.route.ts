import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDemo, Demo } from 'app/shared/model/demo.model';
import { DemoService } from './demo.service';
import { DemoComponent } from './demo.component';
import { DemoDetailComponent } from './demo-detail.component';
import { DemoUpdateComponent } from './demo-update.component';

@Injectable({ providedIn: 'root' })
export class DemoResolve implements Resolve<IDemo> {
  constructor(private service: DemoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDemo> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((demo: HttpResponse<Demo>) => {
          if (demo.body) {
            return of(demo.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Demo());
  }
}

export const demoRoute: Routes = [
  {
    path: '',
    component: DemoComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sitenJhipsterBaseApp.demo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DemoDetailComponent,
    resolve: {
      demo: DemoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sitenJhipsterBaseApp.demo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DemoUpdateComponent,
    resolve: {
      demo: DemoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sitenJhipsterBaseApp.demo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DemoUpdateComponent,
    resolve: {
      demo: DemoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sitenJhipsterBaseApp.demo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
