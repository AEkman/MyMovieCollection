
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
    xhr.open('PUT', 'user/1/add/movie');
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify({
      "title": data.Title,
      "year": data.Year,
      "imdbId": data.imdbID,
      "poster": data.Poster
    }));
}

function delMovieFromUserList(imdbId) {
  var url = `user/1/delete/movie/${ imdbId }`;
  var xhr = new XMLHttpRequest();
  xhr.open('PUT', url);
  xhr.send();
}

//TODO
function getUserMovies() {
  var xhr = new XMLHttpRequest();
  xhr.open('GET', 'http://localhost:8080/user/1/movies');
  xhr.setRequestHeader('Content-Type', 'application/json');
  xhr.onload = function() {
    if(this.status == 200 && this.readyState === 4){
      var data = JSON.parse(this.responseText);
      var result = createModalHtml() +
                  '<table class="table-responsive table-striped">' +
                  '<thead>' +
                  '<tr>' +
                  '<th scope="col">Movie</th>' +
                  '<th scope="col"></th>' +
                  '<th scope="col"></th>' +
                  '</tr>' +
                  '</thead>' +
                  '<tbody>';
      for(var i = 0; i < data.length; i++) {
        console.log(data);
        result += '<tr>' +
                  '<td scope="row"><img class="tableImg" src="' + data[i].poster + '"/></td>' +
                  '<td>' + data[i].title+ '</td>' +
                  '<td><a href="#" class="infoMovie info btn btn-small btn-info" id="' + data[i].imdbId + '" data-toggle="modal" data-target="#infoModal"><i class="fa fa-info"></i> Info</a></td>' +
                  '<td><a href="#" class="add removeFromLayout" id="' +  data[i].imdbId  + '"><i  id="fas fa-heart" class="fas fa-heart fa-2x"></i></a></td>' +
                  '</tr>';
      }
      result += '</tbody></table>';
      document.getElementById('content').innerHTML = result;
      addEventListenerToRemoveFromLayout();
    }
  }
  xhr.send();
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
  xhr.open('GET', 'user/1/movies');
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
