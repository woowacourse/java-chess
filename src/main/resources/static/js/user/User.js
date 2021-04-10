export class User {
  #name
  #winCount
  #loseCount
  #createdTime

  constructor(plainUser) {
    this.#name = plainUser["name"];
    this.#winCount = plainUser["winCount"];
    this.#loseCount = plainUser["loseCount"];
    this.#createdTime = plainUser["createdTime"];
  }

  get name() {
    return this.#name;
  }

  get winCount() {
    return this.#winCount;
  }

  get loseCount() {
    return this.#loseCount;
  }

  get createdTime() {
    return this.#createdTime;
  }
}
