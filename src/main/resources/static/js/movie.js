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
            createSearchResultCardLayout(data);
        });
    });

    $content.on('click', ".addMovie", function(event) {
        var imdbId = $(this).attr('id');
        $.get(url, { apikey: apiKey, r: "json", i: imdbId}, function(data) {
            var movie = { title: data.Title, year: data.Year, imdbId: data.imdbID, poster: data.Poster };

        });
    });

    function createSearchResultCardLayout(data) {
        var result =  createModalHtml() +
            '<div class="row">';
        $.each(data.Search, function (index, item) {
            result += '<div class="col-xs-2>"> ' +
                '<div class="card cardStyle">' +
                '<img class="card-img-top" src="' + item.Poster + '"/> '+
                '<div class="card-body"> ' +
                '<h6 class="card_title">' + item.Title + '</h6>' +
                '</div>' +
                '<div class="card-footer bg-transparent">' +
                '<a href="#" class="infoMovie info btn btn-small btn-info" id="' + item.imdbID + '" data-toggle="modal" data-target="#infoModal"><i class="fa fa-info"></i> Info</a>' +
                '<a href="#" class="add addMovie" id="' +  item.imdbID  + '"><i  class="fa fa-heart fa-2x"></i></a>' +
                '</div>' +
                '</div>' +
                '</div>';
        });

        result += '</div>';
        $content.append(result);
    }

    $content.on('click', '.infoMovie', function(event) {
        event.preventDefault;
        var imdbId = $(this).attr('id');
        $.get(url, { apikey: apiKey, r: "json", i: imdbId}, function(data) {
            $('#modalTitle').text(data.Title);
            $('#modalContent').text(data.Plot);
            $('#year').text(data.Year);
            $('#genre').text(data.Genre);
            $('#imdbRating').text(data.imdbRating);
            $('#released').text(data.Released);
            $('#runtime').text(data.Runtime);
            $('#language').text(data.Language);
            $('#director').text(data.Director);
            $('#writer').text(data.Writer);
            $('#actors').text(data.Actors);
        });
    });

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

});
