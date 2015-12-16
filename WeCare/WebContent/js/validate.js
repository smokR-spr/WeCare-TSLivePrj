/**
 * To check the registration form completion
 */
function checkRegFormCompletion(srcForm) {
	var eles = srcForm.form.elements;
	var incomplete = true;
	for(ele in eles) {
		//if(ele.type == 'text' || ele.type == '')
		if(ele == null || ele.value == "") {
			incomplete = true;
			ele.style.boxShadow = "2px 2px 2px red";
		}
		else {
			incomplete = false;
			ele.style.boxShadow = "none";
		}
	}
	
	if(incomplete) {
		document.getElementById('msgErr').innerHTML = "Please enter all details."
	}
}