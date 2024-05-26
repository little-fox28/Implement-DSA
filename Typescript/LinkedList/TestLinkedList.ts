import {DBLinkedList} from "./DBLinkedList";

const linkedList: DBLinkedList<number> = new DBLinkedList<number>();
console.log(`isEmpty: ${linkedList.isEmpty()}`);
for (let i = 0; i < 10; i++) {
    linkedList.add(i * 3)
}
console.log(`Default list: ${linkedList.toString()}`)
console.log(`IndexOf: ${linkedList.indexOf(11)}`)
console.log(`IndexOf: ${linkedList.indexOf(27)}`)
console.log(`isEmpty: ${linkedList.isEmpty()}`);
console.log(`sizeList: ${linkedList.sizeList()}`);
console.log(linkedList.toString())
console.log(`Add first: ${linkedList.addFirst(11)}`)
console.log(`Add last: ${linkedList.addLast(28)}`)
console.log(linkedList.toString())
console.log(`Get first: ${linkedList.peekFirst()}`)
console.log(`Get last: ${linkedList.peekLast()}`)
linkedList.removeObj(21)
linkedList.removeAt(5)
console.log(`Remove Obj and At: ${linkedList.toString()}`)

const node = linkedList.getHead()!.getNext()!.getNext();
console.log(`Remove Node: ${linkedList.remove(node)}`)

linkedList.clear()
console.log(`Clear: ${linkedList.toString()}`)
