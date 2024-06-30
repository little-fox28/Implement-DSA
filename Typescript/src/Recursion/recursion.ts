function fib(element: number): number {
  if (element < 2) return 1;

  return fib(element - 1) + fib(element - 2);
}

console.log(fib(1));
console.log(fib(4));
console.log(fib(6));
