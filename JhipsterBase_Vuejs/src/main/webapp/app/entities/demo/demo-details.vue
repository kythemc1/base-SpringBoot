<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="demo">
                <h2 class="jh-entity-heading"><span v-text="$t('sitenJhipsterBaseApp.demo.detail.title')">Demo</span> {{demo.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('sitenJhipsterBaseApp.demo.name')">Name</span>
                    </dt>
                    <dd>
                        <span>{{demo.name}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('sitenJhipsterBaseApp.demo.description')">Description</span>
                    </dt>
                    <dd>
                        <span>{{demo.description}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="demo.id" :to="{name: 'DemoEdit', params: {demoId: demo.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./demo-details.component.ts">
</script>
