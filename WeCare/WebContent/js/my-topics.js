/**
 * my-topics.js
 */

var main = function() {
	/*$("#edit-button-form").submit(function(e) {
	    e.preventDefault();
	});*/
	
	
	
	/*$('#edit').click(function() {*/
	$('#edit-window .hidden-form').click(function(e) {
		/*$("#edit-topic").attr('action',"/EditTopic");
		$("#edit-topic").submit();*/
		/*alert($("edit-button-form").serialize());*/
		var target = $(e.target);
		/*alert(e.target.id);*/
		
		var topicNos = parseInt($("#topic-nos").text());
		/*alert("topicNos: " + topicNos);*/
		var topicID = -1;
		i=1;
		while(i <= topicNos) {
			if(target.is("#edit-" + i)) {
				topicID = $("#edit-topic-id-" + i).val();
			}
			i++;
		}
		/*alert("topicID: " + topicID);*/
		/*alert("edit clicked!");*/
		/*var topicID = $("topic-id").val();
		alert(topicID);*/
		var dataString = "topic-id=" + topicID;
		/*alert(topicID + ", " + dataString);*/
		$.ajax({
		   type: "get",
		   url: "GetTopic",
		   data: dataString,/*$("#edit-button-form").serialize(),*/
		   /*dataType: json,*/
		   success: function(data){
			   /*alert("test");
			   alert("json = " + data);
			   alert(data.topicName);
			   //converting jsonObject to normal object
			   var topic = JSON.parse(data);JSON && JSON.parse(json) || $.parseJSON(json);
			   alert("topic = " + topic);
			   $('#edit-topic').attr("topic", topic);
			   alert("topic.topicName = " + topic.topicName);*/
			   /*alert("success");*/
			   //$("input[id='edit-topic-id']").attr('value', data.topicId);
			  /* $("#edit-topic-id").attr('value', data.topicId);
			   alert($("input[id='edit-topic-id']").val());
			   //$("input[id='edit-topic-name']").attr('value', data.topicName);
			   $("#edit-topic-name").attr('value', data.topicName);
			   alert($("input[id='edit-topic-name']").val());
			  */
			   
			   document.getElementById("edit-topic-id").value = data.topicID;
			   document.getElementById("edit-topic-name").value = data.topicName;
			   
			   $("#edit_topic-name").html("<em><u>" + data.topicName + "</u></em>");
			   $("#edit_comment").html(data.comment);
		   },
		   error: function(jqXHR, textStatus, errorThrown) {
				$(document).attr("msgerr", jqXHR.statusText);
		   }
		});
		$('#edit-div').fadeIn();
		return false;
	});
	
	$(document).mouseup(function (e) {
	    var container = $("#edit-div");

	    if (!container.is(e.target) // if the target of the click isn't the container...
	        && container.has(e.target).length === 0) // ... nor a descendant of the container
	    {
	        container.fadeOut();
	    }
	});

}//main

$(document).ready(main);