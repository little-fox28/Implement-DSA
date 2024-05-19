export class StringBuilder {
  private elements: any[];

  constructor() {
    this.elements = [];
  }

  append(element: any): void {
    this.elements.push(element);
  }

  toString(): string {
    return this.elements.join("");
  }
}
