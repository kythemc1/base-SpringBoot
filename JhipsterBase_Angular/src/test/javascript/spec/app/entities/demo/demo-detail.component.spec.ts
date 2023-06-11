import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SitenJhipsterBaseTestModule } from '../../../test.module';
import { DemoDetailComponent } from 'app/entities/demo/demo-detail.component';
import { Demo } from 'app/shared/model/demo.model';

describe('Component Tests', () => {
  describe('Demo Management Detail Component', () => {
    let comp: DemoDetailComponent;
    let fixture: ComponentFixture<DemoDetailComponent>;
    const route = ({ data: of({ demo: new Demo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SitenJhipsterBaseTestModule],
        declarations: [DemoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DemoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DemoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load demo on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.demo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
