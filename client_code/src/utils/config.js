const config = {
    get() {
        return {
            url : process.env.VUE_APP_BASE_API_URL + process.env.VUE_APP_BASE_API + '/',
            name: process.env.VUE_APP_BASE_API,
			menuList:[
				{
					name: '商品信息',
					icon: '',
					child:[

						{
							name:'帮扶商品信息',
							url:'/index/assistanceproductinformationList'
						},
					]
				},
				{
					name: '资讯',
					icon: '',
					child:[

						{
							name:'公告信息',
							url:'/index/newsList'
						},

						{
							name:'论坛交流',
							url:'/index/forumList'
						},
					]
				},
				{
					name: '扶贫资讯管理',
					icon: '',
					child:[

						{
							name:'扶贫资讯',
							url:'/index/povertyalleviationinformationList'
						},
					]
				},
				{
					name: '留言板管理',
					icon: '',
					child:[

						{
							name:'留言板',
							url:'/index/messagesList'
						},
					]
				},
			]
        }
    },
    getProjectName(){
        return {
            projectName: "SpringBoot+Vue助农平台"
        } 
    }
}
export default config
