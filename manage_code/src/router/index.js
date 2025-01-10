	import {
		createRouter,
		createWebHashHistory
	} from 'vue-router'
	import news from '@/views/news/list'
	import discussassistanceproductinformation from '@/views/discussassistanceproductinformation/list'
	import discusspovertyalleviationinformation from '@/views/discusspovertyalleviationinformation/list'
	import address from '@/views/address/list'
	import merchant from '@/views/merchant/list'
	import assistanceproductinformation from '@/views/assistanceproductinformation/list'
	import storeup from '@/views/storeup/list'
	import povertyalleviationinformation from '@/views/povertyalleviationinformation/list'
	import users from '@/views/users/list'
	import productclassification from '@/views/productclassification/list'
	import cart from '@/views/cart/list'
	import forum from '@/views/forum/list'
	import informationclassification from '@/views/informationclassification/list'
	import messages from '@/views/messages/list'
	import orders from '@/views/orders/list'
	import config from '@/views/config/list'
	import user from '@/views/user/list'
	import usersCenter from '@/views/users/center'
	import merchantRegister from '@/views/merchant/register'
	import merchantCenter from '@/views/merchant/center'

export const routes = [{
		path: '/login',
		name: 'login',
		component: () => import('../views/login.vue')
	},{
		path: '/',
		name: '首页',
		component: () => import('../views/index'),
		children: [{
			path: '/',
			name: '首页',
			component: () => import('../views/HomeView.vue'),
			meta: {
				affix: true
			}
		}, {
			path: '/updatepassword',
			name: '修改密码',
			component: () => import('../views/updatepassword.vue')
		}
		
		,{
			path: '/usersCenter',
			name: '管理员个人中心',
			component: usersCenter
		}
		,{
			path: '/merchantCenter',
			name: '商家个人中心',
			component: merchantCenter
		}
		,{
			path: '/news',
			name: '公告信息',
			component: news
		}
		,{
			path: '/discussassistanceproductinformation',
			name: '帮扶商品信息',
			component: discussassistanceproductinformation
		}
		,{
			path: '/discusspovertyalleviationinformation',
			name: '扶贫资讯',
			component: discusspovertyalleviationinformation
		}
		,{
			path: '/address',
			name: '地址',
			component: address
		}
		,{
			path: '/merchant',
			name: '商家',
			component: merchant
		}
		,{
			path: '/assistanceproductinformation',
			name: '帮扶商品信息',
			component: assistanceproductinformation
		}
		,{
			path: '/storeup',
			name: '我的收藏',
			component: storeup
		}
		,{
			path: '/povertyalleviationinformation',
			name: '扶贫资讯',
			component: povertyalleviationinformation
		}
		,{
			path: '/users',
			name: '管理员',
			component: users
		}
		,{
			path: '/productclassification',
			name: '商品分类',
			component: productclassification
		}
		,{
			path: '/cart',
			name: '购物车',
			component: cart
		}
		,{
			path: '/forum',
			name: '论坛交流',
			component: forum
		}
		,{
			path: '/informationclassification',
			name: '资讯分类',
			component: informationclassification
		}
		,{
			path: '/messages',
			name: '留言板',
			component: messages
		}
		,{
			path: '/orders',
			name: '订单管理',
			component: orders
		}
		,{
			path: '/config',
			name: '轮播图',
			component: config
		}
		,{
			path: '/user',
			name: '用户',
			component: user
		}
		]
	},
	{
		path: '/merchantRegister',
		name: '商家注册',
		component: merchantRegister
	},
]

const router = createRouter({
	history: createWebHashHistory(process.env.BASE_URL),
	routes
})

export default router
