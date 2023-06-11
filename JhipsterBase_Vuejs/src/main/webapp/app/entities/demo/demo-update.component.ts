import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { IDemo, Demo } from '@/shared/model/demo.model';
import DemoService from './demo.service';

const validations: any = {
  demo: {
    name: {},
    description: {},
  },
};

@Component({
  validations,
})
export default class DemoUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('demoService') private demoService: () => DemoService;
  public demo: IDemo = new Demo();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.demoId) {
        vm.retrieveDemo(to.params.demoId);
      }
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.demo.id) {
      this.demoService()
        .update(this.demo)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('sitenJhipsterBaseApp.demo.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.demoService()
        .create(this.demo)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('sitenJhipsterBaseApp.demo.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveDemo(demoId): void {
    this.demoService()
      .find(demoId)
      .then(res => {
        this.demo = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
