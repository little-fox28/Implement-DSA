"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.StringBuilder = void 0;
var StringBuilder = /** @class */ (function () {
    function StringBuilder() {
        this.elements = [];
    }
    StringBuilder.prototype.append = function (element) {
        this.elements.push(element);
    };
    StringBuilder.prototype.toString = function () {
        return this.elements.join("");
    };
    return StringBuilder;
}());
exports.StringBuilder = StringBuilder;
