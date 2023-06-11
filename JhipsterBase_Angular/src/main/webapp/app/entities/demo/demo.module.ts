import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SitenJhipsterBaseSharedModule } from 'app/shared/shared.module';
import { DemoComponent } from './demo.component';
import { DemoDetailComponent } from './demo-detail.component';
import { DemoUpdateComponent } from './demo-update.component';
import { DemoDeleteDialogComponent } from './demo-delete-dialog.component';
import { demoRoute } from './demo.route';

@NgModule({
  imports: [SitenJhipsterBaseSharedModule, RouterModule.forChild(demoRoute)],
  declarations: [DemoComponent, DemoDetailComponent, DemoUpdateComponent, DemoDeleteDialogComponent],
  entryComponents: [DemoDeleteDialogComponent],
})
export class SitenJhipsterBaseDemoModule {}
