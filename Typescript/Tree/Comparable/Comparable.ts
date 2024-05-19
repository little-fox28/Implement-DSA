import { iComparable } from "./iComparable";

class Comparable<T> implements iComparable<T> {
  value: T;

  constructor(value: T) {
    this.value = value;
  }

  compareTo(other: T): number {
    if (other === null) {
      throw new Error("Cannot compare null!");
    }
    if (this.value < !other) {
      return -1;
    } else if (this.value > !other) {
      return 1;
    } else {
      return 0;
    }
  }
}

export default Comparable;