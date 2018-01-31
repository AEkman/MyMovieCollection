$(document).ready(function(){
  var $content = $('#content');
  var url = "http://www.omdbapi.com/";
  var apiKey = "86e1e674";

  $('#searchInput').on('change', function (event){
    event.preventDefault();
    $content.empty();
    var $searchInput = $('#searchInput').val();

    $.get(url, { apikey: apiKey, r: "json", s: $searchInput }, function(data) {
      console.log(data);
      createTable(data);
    });
  });

  $content.on('click', ".addMovie", function(event) {
    var imdbId = $(this).attr('id');
    //Change url to backend, and get to post.
    $.get(url, { apikey: apiKey, r: "json", i: imdbId}, function(data) {
      console.log(data);
    });
  });

  function createTable(data){
    var movieSearchResult = "";
    $.each(data.Search, function (index, item) {
        console.log(item);
        movieSearchResult += '<div class="gallery">' +
          '<img src=" ' + item.Poster + '" /> ' +
          '<div class"desc">' + item.Title + '<br/> ' +
          '<button type="button" id="' + item.imdbID + '"class="infoMovie btn btn-primary btn-xs">Info</button>' +
          '<button type="button" id="' + item.imdbID + '"class="addMovie btn btn-primary btn-xs">Add</button>' +
          '</div>' +
          '</div>';
    });

    $content.append(movieSearchResult);
  }
});
