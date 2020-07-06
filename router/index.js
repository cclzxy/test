import Vue from 'vue'
import VueRouter from 'vue-router'
import myStudy from '../views/myStudy.vue'
import mySelf from '../views/mySelf.vue'
import AllTables from '../views/allTables.vue'
Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'myStudy',
        component: myStudy
    }, {
        path: '/mySelf',
        name: 'mySelf',
        component: mySelf
    }, {
        path: '/AllTables',
        name: 'AllTables',
        component: AllTables
    }
]

const router = new VueRouter({
    routes
})

export default router
