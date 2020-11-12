// Handle click checkboxes
let imgCheckboxes = document.getElementsByName("img-checkbox");
let albumCheckboxes = document.getElementsByName("album-checkbox");
let btnDeleteItems = document.getElementById("btn-delete-items");
let btnAddImgToAlbum  = document.getElementById("btn-add-image-to-album");

let albumIds = [];
let imgIds = [];

imgCheckboxes.forEach(imgCheckbox => {
    imgCheckbox.onchange = (e)=>{
        let target = e.target;
        let id = +target.value;
        if(target.checked){
            imgIds.push(id);
        }else{
            imgIds.splice(imgIds.indexOf(id), 1);
        }
        console.log(imgIds);
    }
});

albumCheckboxes.forEach(albumCheckbox => {
    albumCheckbox.onchange = (e)=>{
        let target = e.target;
        let id = +target.value;
        if(target.checked){
            albumIds.push(id);
        }else{
            albumIds.splice(albumIds.indexOf(id), 1);
        }
        console.log(albumIds);
    }

});
