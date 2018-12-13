var calcshistory = [];

$(document).ready(function () {
    $("button").click(function () {
        var coeffs = {
            'a': $("input[name='a']").val(),
            'b': $("input[name='b']").val(),
            'c': $("input[name='c']").val()
        };
        if (isValid(coeffs)) {
            $.post("calc",
                JSON.stringify(coeffs),
                function (data, status) {
                    calcshistory.push(data);
                    createTable(calcshistory);
                });
        }
    });
});

function isValid(coefs) {
    var valid = true;
    $("input:text").attr("class", "validimput");
    var a, b, c;

    if (isNaN(coefs['a']) || coefs['a'] == "") {
        $("input[name='a']").attr("class", "invalidimput");
        valid = false;
    }
    if (isNaN(coefs['b']) || coefs['b'] == "") {
        $("input[name='b']").attr("class", "invalidimput");
        valid = false;
    }
    if (isNaN(coefs['c']) || coefs['c'] == "") {
        $("input[name='c']").attr("class", "invalidimput");
        valid = false;
    }
    if (valid == true) {
        var a = parseFloat(coefs['a']);
        var b = parseFloat(coefs['b']);
        var c = parseFloat(coefs['c']);
        if ((a == 0)) {
            if (b == 0) {
                if (c == 0) {
                    calcshistory.push({num: "-1"});
                    createTable(calcshistory);
                    valid = false;
                } else {
                    calcshistory.push({num: "0"});
                    createTable(calcshistory);
                    valid = false;
                }
            }
        }
    }
        return valid;
    }

    function createTable(roots) {
        $("table").remove();
        var table = $("<table>").attr("id", "calcs");
        table.append('<tr><th>x1</th> <th>x2</th></tr>');
        for (i = roots.length - 1; i >= 0; i--) {
            if (roots[i].num === '2') {
                table.append('<tr id="' + i + '"><td>' + roots[i].x1 + '</td> <td>' + roots[i].x2 + '</td></tr>');
            } else if (roots[i].num === '1') {
                table.append('<tr id="' + i + '"><td colspan="2">' + roots[i].x1 + '</td></tr>');
            } else if (roots[i].num === '0') {
                table.append('<tr id="' + i + '"><td colspan="2">No roots</tr>');
            } else if (roots[i].num === '-1') {
                table.append('<tr id="' + i + '"><td colspan="2">x is any number</tr>');
            }
        }
        $("#calcbutton").after(table);
    }

    $(document).ready(function () {
        $("body").on("click", "td", function () {
            var id = $(this).parent().attr("id");
            calcshistory.splice(id, 1);
            createTable(calcshistory);
        });
    });