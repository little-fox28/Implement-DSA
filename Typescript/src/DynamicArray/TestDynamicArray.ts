import {DynamicArray} from "./DynamicArray";

let array: DynamicArray<number> = new DynamicArray<number>();

console.log(`isEmpty: ${array.isEmpty()}`)

for (let i = 0; i < 19; i++) {
    array.add(i * 2)
}

console.log(`Capacity: ${array.volume()}`)
console.log(`Size: ${array.length()}`)
console.log(`isEmpty: ${array.isEmpty()}`)

console.log(`getAt: ${array.getAt(4)}`)
console.log(`setAt: ${array.setAt(4, 100)}`)
console.log(`indexOf: ${array.indexOf(100)}`)
console.log(`indexOf: ${array.indexOf(8)}`)
console.log(`Contains true: ${array.contains(100)}`)
console.log(`Contains false: ${array.contains(2808)}`)
console.log(`remove: ${array.removeAt(1)}`)
console.log(`remove: ${array.remove(6)}`)
console.log(`toString After remove: ${array.toString()}`);



console.log(`Capacity: ${array.volume()}`)
console.log(`Size: ${array.length()}`)
console.log(`toString: ${array.toString()}`);

array.clear();
console.log(`toString After Clear: ${array.toString()}`);
