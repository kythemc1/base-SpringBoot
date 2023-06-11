import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'demo',
        loadChildren: () => import('./demo/demo.module').then(m => m.SitenJhipsterBaseDemoModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class SitenJhipsterBaseEntityModule {}
