$(function () {
    $("#jqGrid").jqGrid({
        url: '/activiti/model/list',
        datatype: "json",
        colModel: [			
			{ label: '模型ID', name: 'id', width: 100, key: true },
			{ label: '模型标识', name: 'key', width: 70},
			{ label: '模型名称', name: 'name', width: 100 },
			{ label: '版本号', name: 'version', width: 100 },
			{ label: '创建时间', name: 'createTime', width: 100 },
			{ label: '最后更新时间', name: 'lastUpdateTime', width: 100 },
			{ label: '操作', name: '', width: 100,formatter:operationFmt}
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50,100,200],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
    
    function operationFmt (cellvalue, options, row){
    	var e = '<a  class="btn btn-primary btn-sm" href="#" mce_href="#" title="编辑" onclick="edit(\''
		+ row.id
		+ '\')"><i class="fa fa-edit "></i></a> ';
	var d = '<a class="btn btn-warning btn-sm" href="#" title="删除"  mce_href="#" onclick="remove(\''
		+ row.id
		+ '\')"><i class="fa fa-remove"></i></a> ';
	var f = '<a class="btn btn-success btn-sm" href="#" title="部署流程"  mce_href="#" onclick="deploy(\''
		+ row.id
		+ '\')"><i class="fa fa-plug"></i></a> ';
	return e + d + f;
    }
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			tableName: null
		}
	},
	methods: {
		query: function () {
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'tableName': vm.q.tableName},
                page:1 
            }).trigger("reloadGrid");
		},
		generator: function() {
			var tableNames = getSelectedRows();
			if(tableNames == null){
				return ;
			}
			location.href = "sys/generator/code?tables=" + JSON.stringify(tableNames);
		}
	}
});
function reLoad(){
	$("#jqGrid").jqGrid('setGridParam',{ 
        page:1 
    }).trigger("reloadGrid");
}
var prefix = "/activiti/model";
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/"+id,
			type : "delete",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code == 0) {
					layer.msg("删除成功！");
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	})
}
function edit(id) {
	var page = layer.open({
		type : 2,
		title : '修改模型',
		maxmin : true,
		shadeClose : false,
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id
	});
	layer.full(page);
}

function deploy(id) {
    layer.confirm('确定要部署选中的模型吗？', {
        btn : [ '确定', '取消' ]
    }, function() {
        $.ajax({
            url : prefix+"/deploy/"+id,
            type : "post",
            data : {
                'id' : id
            },
            success : function(r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}