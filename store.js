import Vue from "vue"
import Vuex from "vuex"
Vue.use(Vuex)
export default new Vuex.Store({
    state: {
        studyList: [],
        T001list: [],//表内容([])
        activeTableTitle: '',//当前表名（String）
        tableList: [],//结构列表([])
        activeTableCol: '',//当前的表结构(对象集合)
        activeTableList: '',//当前的表数据（对象集合）
        dataListName: [],//当前表结构（数据库的[]）
        dataId: ''//选中的行对应数据库的id
    },
    getters: {

    },
    mutations: {

    },
    actions: {

    }
})