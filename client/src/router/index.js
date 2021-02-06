import Vue from 'vue'
import Router from 'vue-router'
import Student from '../components/Student'
import Table from '../components/Table'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name:'Student',
      component: Student
    },
    {
      path: '/Table',
      name:'Table',
      component: Table
    }
  ]
})