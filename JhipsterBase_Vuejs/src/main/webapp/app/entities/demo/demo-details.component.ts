import { Component, Vue, Inject } from 'vue-property-decorator';

import { IDemo } from '@/shared/model/demo.model';
import DemoService from './demo.service';

@Component
export default class DemoDetails extends Vue {
  @Inject('demoService') private demoService: () => DemoService;
  public demo: IDemo = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.demoId) {
        vm.retrieveDemo(to.params.demoId);
      }
    });
  }

  public retrieveDemo(demoId) {
    this.demoService()
      .find(demoId)
      .then(res => {
        this.demo = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
