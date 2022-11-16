function sendDataAndReload(method, url, data) {
    // Read CSRF tokens (security measure used by Spring Security)
    const csrf_header = document.querySelector("meta[name='_csrf_header']").getAttribute('content');
    const csrf_token = document.querySelector("meta[name='_csrf']").getAttribute('content');

    // Construct query.
    let http = new XMLHttpRequest();
    http.open(method, url);
    http.setRequestHeader('Content-Type', 'application/json');
    http.setRequestHeader(csrf_header, csrf_token);

    http.onload = function () {
        //document.querySelector('html').innerHTML = http.responseText;
        document.open();
        document.write(http.responseText);
        document.close();
    };

    // Send query.
    http.send(JSON.stringify(data));
}
