import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SitenJhipsterBaseSharedModule } from 'app/shared/shared.module';

import { DocsComponent } from './docs.component';

import { docsRoute } from './docs.route';

@NgModule({
  imports: [SitenJhipsterBaseSharedModule, RouterModule.forChild([docsRoute])],
  declarations: [DocsComponent],
})
export class DocsModule {}
