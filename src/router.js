import Vue from 'vue'
import Router from 'vue-router'
// const Home = resolve => require(['./App.vue'], resolve)
// const IndexPage = resolve => require(['@/components/indexPage.vue'], resolve)


Vue.use(Router) 
// import Home from './App'
// import IndexPage from './components/indexPage'

// const router = new Router({
//     mode:'hash',
//     routes:[
//         { path: '/', name:'App', component: Home },
//         { path: '/IndexPage', name:'IndexPage', component: IndexPage },
//     ]
// })

const routes = [
    { path: '/home', name:'App', component: () => import('./App.vue') },
    { path: '/IndexPage', name:'IndexPage', component: () => import('@/components/indexPage.vue')},
]

const router = new Router({
    mode:'hash',
    routes // (缩写) 相当于 routes: routes
  })
  



export default router