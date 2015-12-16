var upload_number = 2;

$(function () {
	
	//to add another add file button
    $('#upload-form').on("change", "input", function () {
        var prevNum = upload_number - 1;
        var prevA = $(this);
        
        //giving the file name to label
        prevA.parent().children('span').html(this.files[0].name);

        if (confirm('Do you want to add another file?')) {
            //alert(e);

            //adding another file input
            var newL = $('<label></label>').attr({
                id: 'attach' + upload_number,
                class: 'add-file'
            });

            var file = $('<input/>').attr({
                type: 'file',
                name: 'attachment' + upload_number,
                id: 'attachment' + upload_number,
                onchange: 'javascript:addAnother();'
            });

            var span = $('<span></span').html('Attach another file');

            newL.append(file);
            newL.append(span);

            prevA.parent().after(newL);

            upload_number++;
        }
    });
    
    //ajax call to update the table
});