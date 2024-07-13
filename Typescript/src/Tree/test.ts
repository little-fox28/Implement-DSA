import BinarySearchTree from "./BST";
import TreeOrderTraverseType from "./TreeOrderTraverseType";

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
        describe('PreOrderTraverse', () => {
            test('should be empty initially', () => {
                expect(bst.isEmpty()).toBe(true);
            })
            test('should add elements', () => {
                expect(bst.add(5)).toBe(true);
                expect(bst.add(4)).toBe(true);
                expect(bst.add(6)).toBe(true);
                expect(bst.add(7)).toBe(true);
                expect(bst.add(3)).toBe(true);
                expect(bst.add(2)).toBe(true);
                expect(bst.add(10)).toBe(true);
            })
            test('should traverse in pre-order', () => {
                bst.add(5);
                bst.add(4);
                bst.add(6);
                bst.add(7);
                bst.add(3);
                bst.add(2);
                bst.add(10);

                expect(bst.traverse(TreeOrderTraverseType.preOrderTraverse))
                    .toEqual([5, 4, 3, 2, 6, 7, 10]);
            });

            test('should traverse in in-order', () => {
                bst.add(5);
                bst.add(4);
                bst.add(6);
                bst.add(7);
                bst.add(3);
                bst.add(2);
                bst.add(10);

                expect(bst.traverse(TreeOrderTraverseType.inOrderTraverse)).toEqual([2,3,4,5,6,7,10]);
            });

            test('should traverse in post-order', () => {
                bst.add(5);
                bst.add(4);
                bst.add(6);
                bst.add(7);
                bst.add(3);
                bst.add(2);
                bst.add(10);

                expect(bst.traverse(TreeOrderTraverseType.postOrderTraverse)).toEqual([2, 3, 4, 10, 7, 6, 5]);
            });
        })
    })
});