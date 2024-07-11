import BinarySearchTree from "./BST";
import NodeTree from "./NodeTree";

describe('BinarySearchTree', () => {
    let bst: BinarySearchTree;

    beforeEach(() => {
        bst = new BinarySearchTree();
    });

    describe('Basic functionality', () => {
        test('should be empty initially', () => {
            expect(bst.isEmpty()).toBe(true);
            expect(bst.size()).toBe(0);
        });

        test('should add elements', () => {
            expect(bst.add(10)).toBe(true);
            expect(bst.size()).toBe(1);
            expect(bst.contains(10)).toBe(true);
        });

        test('should not add duplicate elements', () => {
            bst.add(10);
            expect(bst.add(10)).toBe(false);
            expect(bst.size()).toBe(1);
        });

        test('should remove elements', () => {
            bst.add(10);
            expect(bst.remove(10)).toBe(true);
            expect(bst.size()).toBe(0);
            expect(bst.contains(10)).toBe(false);
        });

        test('should not remove elements that do not exist', () => {
            expect(bst.remove(10)).toBe(false);
            expect(bst.size()).toBe(0);
        });

        test('should return the correct height', () => {
            expect(bst.height()).toBe(0);
            bst.add(10);
            expect(bst.height()).toBe(1);
            bst.add(5);
            bst.add(15);
            expect(bst.height()).toBe(2);
        });
    })

    describe('Traverse', () => {

    })
});