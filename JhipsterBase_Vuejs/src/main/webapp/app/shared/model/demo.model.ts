export interface IDemo {
  id?: number;
  name?: string;
  description?: string;
}

export class Demo implements IDemo {
  constructor(public id?: number, public name?: string, public description?: string) {}
}
