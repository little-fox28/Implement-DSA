import BinarySearchTree from "./BST";
import TreeADT from "./TreeADT";


const bst: TreeADT = new BinarySearchTree();

bst.add(10)
bst.add(12)
bst.add(8)
bst.add(9)
bst.add(6)

// Round 1
test('isEmpty', function () {
    expect(bst.isEmpty()).toBe(false);
    expect(!bst.isEmpty()).toBe(true);
})

test('Size', function () {
    expect(bst.size()).toBe(5);
    expect(bst.size()).not.toBe(0);
})

test('Height', function () {
    expect(bst.height()).toBe(3);
    expect(bst.height()).not.toBe(0);
})

test('Contains', function () {
    expect(bst.contains(10)).toBe(true);
    expect(bst.contains(12)).toBe(true);
    expect(bst.contains(8)).toBe(true);
    expect(bst.contains(9)).toBe(true);
    expect(bst.contains(6)).toBe(true);
})

// Round 2
test('Remove', function () {
    expect(bst.remove(8)).toBe(true);
})
test('Contains', function () {
    expect(bst.contains(8)).not.toBe(true);
    expect(bst.contains(8)).toBe(false);
})

test('Size', function () {
    expect(bst.size()).toBe(4);
    expect(bst.size()).not.toBe(5);
})

test('Height', function () {
    expect(bst.height()).toBe(3);
    expect(bst.height()).not.toBe(0);
})

test('Logger', function () {
    console.log(bst)
})