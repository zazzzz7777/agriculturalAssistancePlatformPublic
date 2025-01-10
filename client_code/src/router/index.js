import { createRouter, createWebHashHistory } from 'vue-router'
import index from '../views'
import home from '../views/pages/home.vue'
import login from '../views/pages/login.vue'
import newsList from '@/views/pages/news/list'
import forumList from '@/views/pages/forum/list'
import messagesList from '@/views/pages/messages/list'
import userList from '@/views/pages/user/list'
import userDetail from '@/views/pages/user/formModel'
import userAdd from '@/views/pages/user/formAdd'
import userRegister from '@/views/pages/user/register'
import userCenter from '@/views/pages/user/center'
import storeupList from '@/views/pages/storeup/list'
import merchantList from '@/views/pages/merchant/list'
import merchantDetail from '@/views/pages/merchant/formModel'
import merchantAdd from '@/views/pages/merchant/formAdd'
import assistanceproductinformationList from '@/views/pages/assistanceproductinformation/list'
import assistanceproductinformationDetail from '@/views/pages/assistanceproductinformation/formModel'
import assistanceproductinformationAdd from '@/views/pages/assistanceproductinformation/formAdd'
import cartList from '@/views/pages/shop_order/cart'
import addressList from '@/views/pages/shop_address/list'
import productclassificationList from '@/views/pages/productclassification/list'
import productclassificationDetail from '@/views/pages/productclassification/formModel'
import productclassificationAdd from '@/views/pages/productclassification/formAdd'
import informationclassificationList from '@/views/pages/informationclassification/list'
import informationclassificationDetail from '@/views/pages/informationclassification/formModel'
import informationclassificationAdd from '@/views/pages/informationclassification/formAdd'
import povertyalleviationinformationList from '@/views/pages/povertyalleviationinformation/list'
import povertyalleviationinformationDetail from '@/views/pages/povertyalleviationinformation/formModel'
import povertyalleviationinformationAdd from '@/views/pages/povertyalleviationinformation/formAdd'
import order_confirm from '@/views/pages/shop_order/confirm'
import ordersList from '@/views/pages/shop_order/list'

const routes = [{
		path: '/',
		redirect: '/index/home'
	},
	{
		path: '/index',
		component: index,
		children: [{
			path: 'home',
			component: home
		}
		, {
			path: 'newsList',
			component: newsList
		}
		, {
			path: 'forumList',
			component: forumList
		}
		, {
			path: 'messagesList',
			component: messagesList
		}
		, {
			path: 'userList',
			component: userList
		}, {
			path: 'userDetail',
			component: userDetail
		}, {
			path: 'userAdd',
			component: userAdd
		}
		, {
			path: 'userCenter',
			component: userCenter
		}
		, {
			path: 'storeupList',
			component: storeupList
		}
		, {
			path: 'merchantList',
			component: merchantList
		}, {
			path: 'merchantDetail',
			component: merchantDetail
		}, {
			path: 'merchantAdd',
			component: merchantAdd
		}
		, {
			path: 'assistanceproductinformationList',
			component: assistanceproductinformationList
		}, {
			path: 'assistanceproductinformationDetail',
			component: assistanceproductinformationDetail
		}, {
			path: 'assistanceproductinformationAdd',
			component: assistanceproductinformationAdd
		}
		, {
			path: 'cartList',
			component: cartList
		}
		, {
			path: 'addressList',
			component: addressList
		}
		, {
			path: 'productclassificationList',
			component: productclassificationList
		}, {
			path: 'productclassificationDetail',
			component: productclassificationDetail
		}, {
			path: 'productclassificationAdd',
			component: productclassificationAdd
		}
		, {
			path: 'informationclassificationList',
			component: informationclassificationList
		}, {
			path: 'informationclassificationDetail',
			component: informationclassificationDetail
		}, {
			path: 'informationclassificationAdd',
			component: informationclassificationAdd
		}
		, {
			path: 'povertyalleviationinformationList',
			component: povertyalleviationinformationList
		}, {
			path: 'povertyalleviationinformationDetail',
			component: povertyalleviationinformationDetail
		}, {
			path: 'povertyalleviationinformationAdd',
			component: povertyalleviationinformationAdd
		}
		, {
			path: 'order_confirm',
			component: order_confirm
		}
		, {
			path: 'ordersList',
			component: ordersList
		}
		]
	},
	{
		path: '/login',
		component: login
	}
	,{
		path: '/userRegister',
		component: userRegister
	}
]

const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL),
  routes
})

export default router
