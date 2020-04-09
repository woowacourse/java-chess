addClass = function (element, classString) {
    element.className = element.className
        .split(" ")
        .filter(function (name) {
            return name !== classString;
        })
        .concat(classString)
        .join(" ");
};

removeClass = function (element, classString) {
    element.className = element.className
        .split(" ")
        .filter(function (name) {
            return name !== classString;
        })
        .join(" ");
};