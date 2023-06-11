import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const Demo = () => import('@/entities/demo/demo.vue');
// prettier-ignore
const DemoUpdate = () => import('@/entities/demo/demo-update.vue');
// prettier-ignore
const DemoDetails = () => import('@/entities/demo/demo-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
  {
    path: '/demo',
    name: 'Demo',
    component: Demo,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/demo/new',
    name: 'DemoCreate',
    component: DemoUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/demo/:demoId/edit',
    name: 'DemoEdit',
    component: DemoUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/demo/:demoId/view',
    name: 'DemoView',
    component: DemoDetails,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
