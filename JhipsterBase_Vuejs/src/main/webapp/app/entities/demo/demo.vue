<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('sitenJhipsterBaseApp.demo.home.title')" id="demo-heading">Demos</span>
            <router-link :to="{name: 'DemoCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-demo">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('sitenJhipsterBaseApp.demo.home.createLabel')">
                    Create a new Demo
                </span>
            </router-link>
        </h2>
        <b-alert :show="dismissCountDown"
            dismissible
            :variant="alertType"
            @dismissed="dismissCountDown=0"
            @dismiss-count-down="countDownChanged">
            {{alertMessage}}
        </b-alert>
        <br/>
        <div class="alert alert-warning" v-if="!isFetching && demos && demos.length === 0">
            <span v-text="$t('sitenJhipsterBaseApp.demo.home.notFound')">No demos found</span>
        </div>
        <div class="table-responsive" v-if="demos && demos.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('name')"><span v-text="$t('sitenJhipsterBaseApp.demo.name')">Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('description')"><span v-text="$t('sitenJhipsterBaseApp.demo.description')">Description</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'description'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="demo in demos"
                    :key="demo.id">
                    <td>
                        <router-link :to="{name: 'DemoView', params: {demoId: demo.id}}">{{demo.id}}</router-link>
                    </td>
                    <td>{{demo.name}}</td>
                    <td>{{demo.description}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'DemoView', params: {demoId: demo.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'DemoEdit', params: {demoId: demo.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(demo)"
                                   variant="danger"
                                   class="btn btn-sm"
                                   v-b-modal.removeEntity>
                                <font-awesome-icon icon="times"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                            </b-button>
                        </div>
                    </td>
                </tr>
                </tbody>
                <infinite-loading
                    ref="infiniteLoading"
                    v-if="totalItems > itemsPerPage"
                    :identifier="infiniteId"
                    slot="append"
                    @infinite="loadMore"
                    force-use-infinite-wrapper=".el-table__body-wrapper"
                    :distance='20'>
                </infinite-loading>
            </table>
        </div>
        <b-modal ref="removeEntity" id="removeEntity" >
            <span slot="modal-title"><span id="sitenJhipsterBaseApp.demo.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-demo-heading" v-text="$t('sitenJhipsterBaseApp.demo.delete.question', {'id': removeId})">Are you sure you want to delete this Demo?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-demo" v-text="$t('entity.action.delete')" v-on:click="removeDemo()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./demo.component.ts">
</script>
