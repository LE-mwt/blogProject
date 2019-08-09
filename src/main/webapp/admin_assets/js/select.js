
			//全选的功能
		function allcheck(){
			//先得到所有的checkbox
			var ck=document.getElementsByName("ck");//得到一组checkbox  相当于数组
			//循环这一组checkbox让每一个checkbox选中
			for(var i=0;i<ck.length;i++){
				var c=ck[i];//得到一个checkbox
				c.checked=true;//true代表选中
			}
		}
		
		//全不选
		function allnotcheck(){
			//先得到所有的checkbox
			var ck=document.getElementsByName("ck");//得到一组checkbox  相当于数组
			//循环这一组checkbox让每一个checkbox选中
			for(var i=0;i<ck.length;i++){
				var c=ck[i];//得到一个checkbox
				c.checked=false;//false代表不选
			}
		}
		//反选
		function backcheck(){//先得到所有的checkbox
			var ck=document.getElementsByName("ck");//得到一组checkbox  相当于数组
			//循环这一组checkbox让每一个checkbox选中
			for(var i=0;i<ck.length;i++){
				var c=ck[i];//得到一个checkbox
				if(c.checked==true){//如果当前的checkbox是选中的则不让其选中
					ck[i].checked=false;
				}else{
					ck[i].checked=true;
				}
			}
		}
		
		//批量删除
		function alldel(){
			var f=false;
			//得到要删除的主键id
			var arr=[];//用于存要删除的选中的id值
			
			var ck=document.getElementsByName("ck");//得到一组checkbox  相当于数组
			//循环这一组checkbox让每一个checkbox选中
			for(var i=0;i<ck.length;i++){
				if(ck[i].checked==true){//代表选中
					arr.push(ck[i].value);
					f=true;
				}
			}
			
			//alert(arr);
			//跳到删除的servlet里去
			if(f==true){
				if(confirm("您确认要删除吗？"))
					location.href="servlet/DelServlet?ids="+arr;
			}else{
				alert("请至少选中一条进行批量删除");
			}
		}


		