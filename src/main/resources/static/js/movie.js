
var userId = document.cookie;
document.getElementById('myMovies').addEventListener('click', getUserMovies);
document.getElementById('searchInput').addEventListener('keypress', getSearchResult);

function getInfoMovie(){
  var imdbId = this.getAttribute('id');
  var url = `http://www.omdbapi.com/?apikey=86e1e674&r=json&i=${ imdbId }`;
  var xhr = new XMLHttpRequest();
  xhr.open('GET', url);
  xhr.onload = function() {
    if(this.status == 200 && this.readyState == 4) {
      infoMovieModal(JSON.parse(this.responseText));
    }
  }
  xhr.send();
}

function infoMovieModal(data) {
  document.getElementById('modalTitle').innerText = data.Title;
  document.getElementById('modalContent').innerText = data.Plot;
  document.getElementById('year').innerText = data.Year;
  document.getElementById('genre').innerText = data.Genre;
  document.getElementById('imdbRating').innerText = data.imdbRating;
  document.getElementById('released').innerText = data.Released;
  document.getElementById('runtime').innerText = data.Runtime;
  document.getElementById('language').innerText = data.Language;
  document.getElementById('director').innerText = data.Director;
  document.getElementById('writer').innerText = data.Writer;
  document.getElementById('actors').innerText = data.Actors;
}

function addOrDeleteMovieFromUserList(){
  var imdbId = this.getAttribute('id');
  var heartIcon = this.firstElementChild.id;
    if(heartIcon.indexOf("far fa-heart") > -1){
      getMovieBeforeAddToUserList(imdbId);
      this.firstElementChild.setAttribute('class', 'fas fa-heart fa-2x');
      this.firstElementChild.setAttribute('id', 'fas fa-heart');
    }
    else if(heartIcon.indexOf("fas fa-heart") > -1){
      delMovieFromUserList(imdbId);
      this.firstElementChild.setAttribute('class', 'far fa-heart fa-2x');
      this.firstElementChild.setAttribute('id', 'far fa-heart');
    }
}

function getMovieBeforeAddToUserList(imdbId){
  var url = `http://www.omdbapi.com/?apikey=86e1e674&r=json&i=${ imdbId }`;
  var xhr = new XMLHttpRequest();
  xhr.open('GET', url);
  xhr.onload = function() {
    if(this.status == 200 && this.readyState == 4) {
      addMovieToUserList(JSON.parse(this.responseText));
    }
  }
  xhr.send();
}

function addMovieToUserList(data) {
    var xhr = new XMLHttpRequest();
    xhr.open('PUT', `user/${ userId }/add/movie`);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify({
      "title": data.Title,
      "year": data.Year,
      "imdbId": data.imdbID,
      "poster": data.Poster
    }));
}

function delMovieFromUserList(imdbId) {
  var url = `user/${ userId }/delete/movie/${ imdbId }`;
  var xhr = new XMLHttpRequest();
  xhr.open('PUT', url);
  xhr.send();
}

function getUserMovies() {
  var xhr = new XMLHttpRequest();
  xhr.open('GET', `user/${ userId }/movies`);
  xhr.setRequestHeader('Content-Type', 'application/json');
  xhr.onload = function() {
    if(this.status == 200 && this.readyState === 4){
      var data = JSON.parse(this.responseText);
      var result = createModalHtml() +
                  '<div class="container left"><table class="table table-responsive table-striped">' +
                  '<thead>' +
                  '<tr>' +
                  '<th class="width10" scope="col"></th>' +
                  '<th class="width60" scope="col">Title ' +
                  '<a href="#" id="asc"  class="sortTable title"><i class="fas fa-chevron-up"></i></a> ' +
                  '<a href="#" id="desc" class="sortTable title"><i class="fas fa-chevron-down"></i></a> ' +
                  '</th>' +
                  '<th class="width10" scope="col">Year ' +
                  '<a href="#" id="asc" class="sortTable"><i class="fas fa-chevron-up"></i></a> ' +
                  '<a href="#" id="desc" class="sortTable"><i class="fas fa-chevron-down"></i></a> ' +
                  '</th>' +
                  '<th class="width20" scope="col"></th>' +
                  '</tr>' +
                  '</thead>' +
                  '<tbody id="userTable" >';
      for(var i = 0; i < data.length; i++) {
        result += '<tr>' +
                  '<td scope="row"><img class="tableImg" src="' + data[i].poster + '"/></td>' +
                  '<td>' + data[i].title + '</td>' +
                  '<td>' + data[i].year + '</td>' +
                  '<td><a href="#" class="infoMovie infoUser btn btn-small btn-info" id="' + data[i].imdbId + '" data-toggle="modal" data-target="#infoModal"><i class="fa fa-info"></i> Info</a>' +
                  '<a href="#" class="addUser removeFromLayout" id="' +  data[i].imdbId  + '"><i  id="fas fa-heart" class="fas fa-heart fa-2x"></i></a></td>' +
                  '</tr>';
      }
      result += '</tbody></table></div>';
      document.getElementById('content').innerHTML = result;

      addEventListenerToRemoveFromLayout();
      addEventListenerToSortTableMovie();
    }
  }
  xhr.send();
}

function sortTableMovie() {
  var id = this.getAttribute('id');
  var table = document.getElementById('userTable');
  var rows = table.getElementsByTagName('TR');
  var changeRow = true;
  var titleOrYear = this.getAttribute('class');
  var postition = (titleOrYear.indexOf("title") > -1) ? 1 : 2;

  while(changeRow) {
    changeRow = false;
    for(var i = 0; i < (rows.length -1); i++){
      var row = rows[i].getElementsByTagName('TD')[postition];
      var row2 = rows[i + 1].getElementsByTagName('TD')[postition];
      if(id == "asc"){
        if(row.innerText.toLowerCase() < row2.innerText.toLowerCase()){
          rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
          changeRow = true;
        }
      }else if(id === "desc") {
        if(row.innerText.toLowerCase() > row2.innerText.toLowerCase()){
          rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
          changeRow = true;
        }
      }
    }
  }
}

function addEventListenerToSortTableMovie() {
  var sortMovieTable = document.getElementsByClassName('sortTable');
  for(var i = 0; i < sortMovieTable.length; i++) {
    sortMovieTable[i].addEventListener('click', sortTableMovie);
  }
}

function addEventListenerToRemoveFromLayout() {
  var removeFromLayout = document.getElementsByClassName('removeFromLayout');
  var infoMovieClassList = document.getElementsByClassName('infoMovie');
    for (var i = 0; i < removeFromLayout.length; i++) {
       removeFromLayout[i].addEventListener('click', removeFromUserLayout);
       infoMovieClassList[i].addEventListener('click', getInfoMovie);
    }
}

function removeFromUserLayout() {
  var imdbId = this.getAttribute('id');
  delMovieFromUserList(imdbId);
  this.parentNode.parentNode.remove();
}

function getSearchResult(event) {
  if(event.keyCode == 13){
    var searchInput = document.getElementById('searchInput').value;
    var url = `http://www.omdbapi.com/?apikey=86e1e674&r=json&s=${ searchInput }`;
    var xhr = new XMLHttpRequest();
    xhr.open('GET', url);
    xhr.onload = function() {
      if(this.status == 200 && this.readyState == 4){
        createSearchResultCardLayout(JSON.parse(this.responseText));
      }
    }
    xhr.send();
  }
}

function createSearchResultCardLayout(data) {
  var xhr = new XMLHttpRequest();
  xhr.open('GET', `user/${ userId }/movies`);
  xhr.onload = function() {
    if(this.status == 200 && this.readyState == 4){
      var userMovieList = JSON.parse(this.responseText);
      var result =  createModalHtml() +
      '<div class="row">';
      for(var item in data.Search){
        var heartIcon =  (userMovieList.some(x => x.imdbId == data.Search[item].imdbID)) ? "fas fa-heart" : "far fa-heart";
        result += '<div class="col-xs-2>"> ' +
                     '<div class="card cardStyle">' +
                     '<img class="card-img-top" src="' + data.Search[item].Poster + '"/> '+
                     '<div class="card-body"> ' +
                     '<h6 class="card_title">' + data.Search[item].Title + '</h6>' +
                     '</div>' +
                     '<div class="card-footer bg-transparent">' +
                     '<a href="#" class="infoMovie info btn btn-small btn-info" id="' + data.Search[item].imdbID + '" data-toggle="modal" data-target="#infoModal">' +
                     '<i class="fa fa-info"></i> Info</a>' +
                     '<a href="#" class="add addMovie" id="' +  data.Search[item].imdbID  + '"><i  id=" '+heartIcon +' " class="' + heartIcon + ' fa-2x"></i></a>' +
                     '</div>' +
                     '</div>' +
                     '</div>';
      }
      result += '</div>';
           document.getElementById('content').innerHTML = result;
           addEventListenerToAddMovieAndInfoMovie();
    }
  }
  xhr.send();
}

function addEventListenerToAddMovieAndInfoMovie() {
  var addMovieClassList = document.getElementsByClassName('addMovie');
  var infoMovieClassList = document.getElementsByClassName('infoMovie');
  for (var i = 0; i < addMovieClassList.length; i++) {
     addMovieClassList[i].addEventListener('click', addOrDeleteMovieFromUserList);
     infoMovieClassList[i].addEventListener('click', getInfoMovie);
 }
}

function createModalHtml() {
    return '<div id="infoModal" class="modal" tabindex="-1" role="dialog" aria-hidden="true">' +
        '<div class="modal-dialog" role="document">' +
        '<div class="modal-content">' +
        '<div class="modal-header">' +
        '<h5 id="modalTitle" class="modal-title"></h5>' +
        '<button type="button" class="close" data-dismiss="modal" arial-label="close">' +
        '<span aria-hidden="true">&times;</span>' +
        '</button>' +
        '</div>' +
        '<div class="modal-body">' +
        '<p id="modalContent"></p>' +
        '<p>' +
        '<b>Runtime:</b> <span id="runtime"></span></br>' +
        '<b>Year:</b> <span id="year"></span></br>' +
        '<b>Genre:</b> <span id="genre"></span></br>' +
        '<b>ImdbRating:</b> <span id="imdbRating"></span></br>' +
        '<b>Released:</b> <span id="released"></span></br>' +
        '<b>Language:</b> <span id="language"></span></br>' +
        '<b>Director:</b> <span id="director"></span></br>' +
        '<b>Writer:</b> <span id="writer"></span></br>' +
        '<b>Actors:</b> <span id="actors"></span></br>' +
        '</p>' +
        '</div>' +
        '<div class="modal-footer">' +
        '<button type="button" class="close" data-dismiss="modal">Close</button>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>'
}
