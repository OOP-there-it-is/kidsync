
window.purechatApi = { l: [], t: [], on: function () { this.l.push(arguments); } };
(function () { var done = false; var script = document.createElement('script');
script.async = true; script.type = 'text/javascript'; script.src = 'https://app.purechat.com/VisitorWidget/WidgetScript';
document.getElementsByTagName('HEAD').item(0).appendChild(script); script.onreadystatechange = script.onload = function (e)
    { if (!done && (!this.readyState || this.readyState == 'loaded' || this.readyState == 'complete'))
    { var w = new PCWidget({c: '13cbd7ce-a262-406f-9d8c-d296501d2609', f: true }); done = true; } }; })();