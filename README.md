# GitHub-Mobile

Repository for accessibility testing on mobile products.

## Stacked pull request demo

This documentation-only demo splits a change into two dependent pull requests:

1. Add this repository overview, targeting `main`.
2. Add review guidance, targeting the first pull request's branch.

The second branch includes the first branch's commit, keeping each pull request's
diff focused on its own change.

### Reviewing the stack

Review the overview pull request first, then the review-guidance pull request.
The second pull request targets `demo/stacked-docs-base`, not `main`, so its diff
shows only this guidance.

Merge the bottom pull request first. Before merging the remaining pull request,
confirm its base is `main` and its diff contains only the intended follow-up.
