/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import DemoDetailComponent from '@/entities/demo/demo-details.vue';
import DemoClass from '@/entities/demo/demo-details.component';
import DemoService from '@/entities/demo/demo.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Demo Management Detail Component', () => {
    let wrapper: Wrapper<DemoClass>;
    let comp: DemoClass;
    let demoServiceStub: SinonStubbedInstance<DemoService>;

    beforeEach(() => {
      demoServiceStub = sinon.createStubInstance<DemoService>(DemoService);

      wrapper = shallowMount<DemoClass>(DemoDetailComponent, { store, i18n, localVue, provide: { demoService: () => demoServiceStub } });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundDemo = { id: 123 };
        demoServiceStub.find.resolves(foundDemo);

        // WHEN
        comp.retrieveDemo(123);
        await comp.$nextTick();

        // THEN
        expect(comp.demo).toBe(foundDemo);
      });
    });
  });
});
